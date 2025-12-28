
$baseUrl = "http://localhost:9090/api"

function Post-Json {
    param ($endpoint, $data, $token=$null)
    $headers = @{ "Content-Type" = "application/json" }
    if ($token) { $headers["Authorization"] = "Bearer $token" }
    
    $json = $data | ConvertTo-Json -Depth 10
    # Fix encoding for Turkish chars if needed, but usually Powershell 5.1+ handles it. 
    # Simply using Invoke-RestMethod for auto body handling.
    
    try {
        return Invoke-RestMethod -Uri "$baseUrl$endpoint" -Method Post -Body $json -Headers $headers
    } catch {
        # Return the error response to debug
        $stream = $_.Exception.Response.GetResponseStream()
        $reader = New-Object System.IO.StreamReader($stream)
        $body = $reader.ReadToEnd()
        Write-Host "Error POST $endpoint : $body"
        throw $_
    }
}

# 1. Register Admin
$admin = @{
    email = "admin@ticketapp.com"
    sifre = "admin123"
    ad = "Super"
    soyad = "Admin"
    role = "YONETICI"
    telefon = "5551112233"
}
try {
    $res = Post-Json "/auth/register" $admin
    Write-Host "Admin Registered: $($res.message)"
} catch {
    Write-Host "Admin Registration skipped/failed (Check above if 400 or other)"
}

# 2. Register User
$user = @{
    email = "test2@example.com"
    sifre = "123456"
    ad = "Test"
    soyad = "User"
    role = "YOLCU"
    telefon = "5559998877"
}
try {
    $res = Post-Json "/auth/register" $user
    Write-Host "User Registered: $($res.message)"
} catch {
    Write-Host "User Registration skipped/failed"
}

# 3. Login Admin
$loginData = @{ email = "admin@ticketapp.com"; password = "admin123" }
try {
    $loginRes = Post-Json "/auth/login" $loginData
    $token = $loginRes.data.token
    Write-Host "Logged in. Token: $token"
} catch {
    Write-Error "Login failed. Cannot proceed."
    exit 1
}

# 4. Create Firm
try {
    $firm = Invoke-RestMethod -Uri "$baseUrl/firmalar?ad=FinalTestBus" -Method Post -Headers @{ Authorization = "Bearer $token" }
    Write-Host "Firm Created: $($firm.id)"
} catch {
    # If fails, maybe try to find it
    Write-Host "Firm create failed, checking list..."
    $firms = Invoke-RestMethod -Uri "$baseUrl/firmalar" -Method Get -Headers @{ Authorization = "Bearer $token" }
    $firm = $firms | Where-Object { $_.ad -eq "FinalTestBus" } | Select-Object -First 1
}

if (-not $firm) {
    Write-Error "Firm not found or created."
    exit 1
}

# 5. Create Trip
$tripData = @{
    kalkisYeri = "Ankara"
    varisYeri = "Izmir"
    kalkisZamani = "2025-12-28T10:00:00"
    fiyat = 500.0
    koltukDuzeni = "2+1"
    firmaId = $firm.id
    type = "OTOBUS"
}
try {
    $trip = Post-Json "/seferler" $tripData $token
    Write-Host "Trip Created: $($trip.id)"
} catch {
    Write-Host "Trip creation error: $_"
}

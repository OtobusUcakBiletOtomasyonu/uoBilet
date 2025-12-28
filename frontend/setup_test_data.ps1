
$baseUrl = "http://localhost:9090/api"

# 1. Login as Admin
$adminLogin = @{
    email = "admin@ticketapp.com"
    sifre = "admin123"
}
$loginJson = $adminLogin | ConvertTo-Json
try {
    $authResponse = Invoke-RestMethod -Uri "$baseUrl/auth/login" -Method Post -Body $loginJson -ContentType "application/json"
    $token = $authResponse.token
    Write-Host "Admin Logged In. Token length: $($token.Length)"
} catch {
    Write-Error "Admin Login Failed: $_"
    exit 1
}

$headers = @{
    Authorization = "Bearer $token"
}

# 2. Create Firm "FinalTestBus"
try {
    $firm = Invoke-RestMethod -Uri "$baseUrl/firmalar?ad=FinalTestBus" -Method Post -Headers $headers -ContentType "application/json"
    Write-Host "Firm Created: $($firm.ad) (ID: $($firm.id))"
} catch {
    Write-Host "Firm creation failed (maybe exists): $_"
    # Try to find it
    $firms = Invoke-RestMethod -Uri "$baseUrl/firmalar" -Method Get -Headers $headers
    $firm = $firms | Where-Object { $_.ad -eq "FinalTestBus" } | Select-Object -First 1
    if ($firm) {
        Write-Host "Found existing firm: $($firm.ad)"
    } else {
        Write-Error "Could not create or find firm."
        exit 1
    }
}

# 3. Create Trip (Ankara -> Izmir, 2025-12-28 10:00)
# Backend expects: type, kalkis, varis, zaman, fiyat, koltukDuzeni, firmaId
$tripDate = "2025-12-28T10:00:00"
$tripData = @{
    kalkisYeri = "Ankara"
    varisYeri = "Izmir"
    kalkisZamani = $tripDate
    fiyat = 500.0
    koltukDuzeni = "2+1"
    firmaId = $firm.id
    type = "OTOBUS"
}
$tripJson = $tripData | ConvertTo-Json

try {
    $trip = Invoke-RestMethod -Uri "$baseUrl/seferler" -Method Post -Body $tripJson -Headers $headers -ContentType "application/json"
    Write-Host "Trip Created: $($trip.kalkisYeri) -> $($trip.varisYeri) at $($trip.kalkisZamani)"
} catch {
     Write-Error "Trip Creation Failed: $_"
     Write-Host "Body sent: $tripJson"
}

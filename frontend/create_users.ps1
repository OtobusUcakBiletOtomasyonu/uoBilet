
$baseUrl = "http://localhost:9090/api/auth/register"

# Create Admin
$admin = @{
    email = "admin@ticketapp.com"
    sifre = "admin123"
    ad = "Super"
    soyad = "Admin"
    role = "YONETICI"
    telefon = "5551112233"
}

$user = @{
    email = "test2@example.com"
    sifre = "123456"
    ad = "Test"
    soyad = "User"
    role = "YOLCU"
    telefon = "5559998877"
}

function Register-User {
    param ($userData)
    $json = $userData | ConvertTo-Json
    $json = [System.Text.Encoding]::UTF8.GetBytes($json)
    
    try {
        $response = Invoke-RestMethod -Uri $baseUrl -Method Post -Body $json -ContentType "application/json"
        Write-Host "Registered $($userData.email): $($response.message)"
    } catch {
        Write-Host "Failed to register $($userData.email): $_"
    }
}

Register-User $admin
Register-User $user

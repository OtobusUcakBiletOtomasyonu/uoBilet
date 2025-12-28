
$baseUrl = "http://localhost:9090/api"

$trips = @(
    @{
        tip = "OTOBUS"
        kalkisYeri = "Istanbul"
        varisYeri = "Ankara"
        kalkisZamani = "2024-01-01T09:00:00"
        fiyat = 450
        koltukDuzeni = "2+1"
        firmaAdi = "Kamil Koç"
    },
    @{
        tip = "OTOBUS"
        kalkisYeri = "Istanbul"
        varisYeri = "Ankara"
        kalkisZamani = "2024-01-01T14:30:00"
        fiyat = 500
        koltukDuzeni = "2+1"
        firmaAdi = "Pamukkale"
    },
    @{
        tip = "UCAK"
        kalkisYeri = "Istanbul"
        varisYeri = "Izmir"
        kalkisZamani = "2024-01-02T10:00:00"
        fiyat = 1200
        firmaAdi = "THY"
    },
    @{
        tip = "OTOBUS"
        kalkisYeri = "Ankara"
        varisYeri = "Antalya"
        kalkisZamani = "2024-01-03T23:00:00"
        fiyat = 600
        koltukDuzeni = "2+2"
        firmaAdi = "Metro"
    },
    @{
        tip = "OTOBUS"
        kalkisYeri = "Bursa"
        varisYeri = "Adana"
        kalkisZamani = "2025-12-26T14:00:00"
        fiyat = 850
        koltukDuzeni = "2+1"
        firmaAdi = "Nilüfer"
    }
)

foreach ($trip in $trips) {
    $jsonPayload = $trip | ConvertTo-Json -Depth 2
    # Ensure UTF-8 encoding for Turkish characters if needed, mostly ASCII here
    $jsonPayload = [System.Text.Encoding]::UTF8.GetBytes($jsonPayload)

    try {
        Write-Host "Creating trip: $($trip.kalkisYeri) -> $($trip.varisYeri)"
        
        $response = Invoke-RestMethod -Uri "$baseUrl/seferler" -Method Post -Body $jsonPayload -ContentType "application/json"
        
        if ($response.success) {
            $seferId = $response.data
            Write-Host "Trip created with ID: $seferId. Generating seats..."
            
            # Create seats
            $seatResponse = Invoke-RestMethod -Uri "$baseUrl/koltuklar/$seferId" -Method Post
            Write-Host "Seats generated: $($seatResponse.message)"
        } else {
            Write-Error "Failed to create trip: $($response.message)"
        }
    } catch {
        Write-Error "Error creating trip: $_"
    }
    Write-Host "--------------------------------"
}

Write-Host "Seeding complete!"

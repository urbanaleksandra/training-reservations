# GYM

# Funkcionalnosti:
  - Login
  - Logout
  - Registracija - pri registraciji šalje se verifikacioni mail.
  
# Uloge u sistemu: 
  - Admin:
    - uvid u sve treninge koji postoje u sistemu
    - CRUD treninga (kao i rasporeda kada će se održavati isti)
    - uvid u sve korisnike u sistemu
    - unblock korisnika (korisnik je blokiran ukoliko se ne pojavi na više od 5 treninga koje je rezervisao, tada nije u mogućnosti da se prijavi na sistem)
    
  - Zaposleni:
    - ima uvid u treninge koji još nisu prošli
    - potvrđuje dolazak članova na trening skeniranjem njihovog QR koda preko kamere.
    
  - Korisnici teretane:
    - ima uvid u treninge koji još nisu prošli
    - prikaz ličnih podataka, uključujući njegov jedinstveni QR kod
    - mogućnost kontaktiranja teretane (poruka se šalje adminu teretane na mail)
    - rezervisanje treninga

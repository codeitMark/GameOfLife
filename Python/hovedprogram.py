from verden import Verden

def hovedprogram():
    rad = int(input("Hvor mange rader vil du ha?"))
    kol = int(input("Hvor mange kolonner vil du ha?"))
    verden = Verden(rad, kol)
    verden.tegn()
    inputt = input("press enter for å fortsette og q for å stoppe")
    while inputt != "q":
        verden.oppdatering()
        verden.tegn()
        inputt = input("press enter for å fortsette og q for å stoppe")

# starte hovedprogrammet
hovedprogram()
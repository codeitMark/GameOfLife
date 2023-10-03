from rutenett import Rutenett

class Verden:
    def __init__(self, rader, kolonner):
        self._rutenett = Rutenett(rader, kolonner)
        self._generasjonsnummer = 0
        self._rutenett.fyll_med_tilfeldige_celler()
        self._rutenett.koble_celler()

    def tegn(self):
        self._rutenett.tegn_rutenett()
        print(str(self._generasjonsnummer) + ". generasjon")
        print("Antall levende celler:" + str(self._rutenett.antall_levende()))
        print()

    def oppdatering(self):
        celle_liste = self._rutenett.hent_alle_celler()
        for celler in celle_liste:
            celler.tell_levende_naboer()
        for celler in celle_liste:
            celler.oppdater_status()
        self._generasjonsnummer += 1
from random import randint
from celle import Celle

class Rutenett:
    def __init__(self, rader, kolonner):
        self._ant_rader = rader
        self._ant_kolonner = kolonner
        self._rutenett = []
        self._lag_tom_rad()
        self._lag_tomt_rutenett()

    def _lag_tomt_rutenett(self):
        for rad in range(self._ant_rader):
            rad = self._lag_tom_rad()
            self._rutenett.append(rad)
        return self._rutenett

    def _lag_tom_rad(self):
        rader = []
        for kolonne in range(self._ant_kolonner):
            rader.append(None)
        return rader

    def fyll_med_tilfeldige_celler(self):
        for rad in range(self._ant_rader):
            for kol in range(self._ant_kolonner):
                celle = self.lag_celle(rad, kol)

    def lag_celle(self, rad, kol):
        self._rutenett[rad][kol] = Celle()
        if randint(0, 2) == 0:
            self._rutenett[rad][kol].sett_levende()

    def hent_celle(self, rad, kol):
        if rad in range(0, self._ant_rader) and kol in range (0, self._ant_kolonner):
            return self._rutenett[rad][kol]
        else:
            return None
        
    def tegn_rutenett(self):
        for lister in self._rutenett:
            for celle in lister:
                print(celle.hent_status_tegn(), end="")
            print()

    def _sett_naboer(self, rad, kol):
        celle = self.hent_celle(rad, kol)
        for x in range(-1, 2):
            for y in range(-1, 2):
                celle_2 = self.hent_celle(rad + x, kol + y)
                if celle_2 != None:
                    if rad + x == rad and kol + y == kol:
                        pass
                    else:
                        celle.legg_til_nabo(celle_2)
        return len(celle._naboer)
    
    def koble_celler(self):
        for rad in range(self._ant_rader):
            for kol in range(self._ant_kolonner):
                self._sett_naboer(rad, kol)

    def hent_alle_celler(self):
        alle_celler = []
        for lister in self._rutenett:
            for celler in lister:
                if isinstance(celler, Celle):
                    alle_celler.append(celler)
        return alle_celler

    def antall_levende(self):
        levende = 0
        for lister in self._rutenett:
            for celler in lister:
                leve = celler.er_levende()
                if leve == True:
                    levende += 1
        return levende
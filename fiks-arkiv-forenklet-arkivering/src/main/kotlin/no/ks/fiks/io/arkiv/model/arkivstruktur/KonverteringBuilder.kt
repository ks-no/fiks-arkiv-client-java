package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivstruktur.Konvertering
import java.time.ZonedDateTime

class KonverteringBuilder {

    var konvertertDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var konvertertAv: String? = null
        private set
    var konvertertFraFormat: String? = null
        private set
    var konvertertTilFormat: String? = null
        private set
    var konverteringsverktoey: String? = null
        private set
    var konverteringskommentar: String? = null
        private set

    fun konvertertDato(konvertertDato: ZonedDateTime) = apply { this.konvertertDato = konvertertDato }
    fun konvertertAv(konvertertAv: String) = apply { this.konvertertAv = konvertertAv }
    fun konvertertFraFormat(konvertertFraFormat: String) = apply { this.konvertertFraFormat = konvertertFraFormat }
    fun konvertertTilFormat(konvertertTilFormat: String) = apply { this.konvertertTilFormat = konvertertTilFormat }
    fun konverteringsverktoey(konverteringsverktoey: String) = apply { this.konverteringsverktoey = konverteringsverktoey }
    fun konverteringskommentar(konverteringskommentar: String) = apply { this.konverteringskommentar = konverteringskommentar }

    fun build() : Konvertering {
        return Konvertering().also {
            it.konvertertDato = konvertertDato
            it.konvertertAv = checkNotNull(konvertertAv) {"KonvertertAv er påkrevd for Konvertering"}
            it.konvertertFraFormat = checkNotNull(konvertertFraFormat) {"KonvertertFraFormat er påkrevd for Konvertering"}
            it.konvertertTilFormat = checkNotNull(konvertertTilFormat) {"KonvertertTilFormat er påkrevd for konvertering"}
            it.konverteringsverktoey = konverteringsverktoey
            it.konverteringskommentar = konverteringskommentar
        }
    }
}

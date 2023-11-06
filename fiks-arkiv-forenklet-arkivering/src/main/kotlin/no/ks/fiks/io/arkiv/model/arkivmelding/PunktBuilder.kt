package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Punkt
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Koordinatsystem

class PunktBuilder {
    var koordinatsystem: Koordinatsystem? = null
        private set
    var x: Double? = null
        private set
    var y: Double? = null
        private set
    var z: Double? = null
        private set

    fun koordinatsystem(koordinatsystem: Koordinatsystem) = apply { this.koordinatsystem = koordinatsystem }
    fun x(x: Double) = apply { this.x = x }
    fun y(y: Double) = apply { this.y = y }
    fun z(y: Double) = apply { this.z = z }

    fun build() : Punkt {
        return Punkt().also {
            it.koordinatsystem = checkNotNull(koordinatsystem) {"Koordinatsystem er påkrevd for Punkt"}
            it.x = checkNotNull(x) {"X er påkrevd for Punkt"}
            it.y = checkNotNull(y) {"Y er påkrevd for Punkt"}
            it.z = z
        }
    }
}

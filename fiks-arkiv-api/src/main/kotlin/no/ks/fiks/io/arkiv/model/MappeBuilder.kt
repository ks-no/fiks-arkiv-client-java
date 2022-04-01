package no.ks.fiks.io.arkiv.model

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Mappe
import java.time.ZonedDateTime


class MappeBuilder {
    var systemID: SystemIDBuilder? = null
        private set
    var mappeId: String? = null
        private set
    var referanseForeldermappe: SystemIDBuilder? = null
        private set
    var tittel: String? = null
        private set
    var opprettetDato: ZonedDateTime? = null
        private set
    var opprettetAv: String? = null
        private set
    var avsluttetDato: ZonedDateTime? = null
        private set
    var avsluttetAv: String? = null
        private set


    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun mappeId(mappeId: String) = apply { this.mappeId = mappeId }
    fun referanseForeldermappe(referanseForeldermappe: SystemIDBuilder) = apply { this.referanseForeldermappe = referanseForeldermappe }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun avsluttetDato(avsluttetDato: ZonedDateTime) = apply { this.avsluttetDato = avsluttetDato }
    fun avsluttetAv(avsluttetAv: String) = apply { this.avsluttetAv = avsluttetAv }

    fun build(): Mappe {
        return Mappe().also {
            it.systemID = systemID?.buildApiModel()
            it.mappeID = mappeId
            it.referanseForeldermappe = referanseForeldermappe?.buildApiModel()
            it.tittel = tittel
            it.opprettetDato = opprettetDato
            it.opprettetAv = opprettetAv
            it.avsluttetDato = avsluttetDato
            it.avsluttetAv = avsluttetAv
        }
    }
}

package no.ks.fiks.io.arkiv.model

interface Registrering<T: no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.Registrering> {
    fun buildApiModel(): T
}
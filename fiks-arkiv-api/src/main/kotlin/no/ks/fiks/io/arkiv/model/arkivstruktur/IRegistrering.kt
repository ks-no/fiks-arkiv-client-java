package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Registrering

interface IRegistrering {
    fun buildApiModel(): Registrering
}
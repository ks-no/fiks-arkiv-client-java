package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Registrering


/**
 * Registrering er det overordnede objektet for å samle et sett med dokumenter som hører sammen, eller for å angi en hendelse.
 */
interface IRegistrering {
    fun build(): Registrering
}
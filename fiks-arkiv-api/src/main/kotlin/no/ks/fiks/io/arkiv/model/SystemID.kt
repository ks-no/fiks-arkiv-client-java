package no.ks.fiks.io.arkiv.model

import java.util.*

data class SystemID(val value: UUID, val label: String) {
    fun buildApiModel(): no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.SystemID {
        return no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.SystemID().also {
            it.value = value.toString()
            it.label = label
        }
    }
}


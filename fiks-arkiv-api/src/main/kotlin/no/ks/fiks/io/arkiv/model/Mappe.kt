package no.ks.fiks.io.arkiv.model


class Mappe (val systemID: SystemID, val mappeId: String, val referanseForeldermappe: SystemID, val tittel: String) {

    fun buildApiModel(): no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.Mappe {
        return no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.Mappe().also {
            it.systemID = systemID.buildApiModel()
            it.mappeID = mappeId
            it.referanseForeldermappe = referanseForeldermappe.buildApiModel()
            it.tittel = tittel
        }
    }
}

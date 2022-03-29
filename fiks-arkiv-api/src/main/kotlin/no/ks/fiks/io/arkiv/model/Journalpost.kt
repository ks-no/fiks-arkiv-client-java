package no.ks.fiks.io.arkiv.model

import java.util.*
import javax.xml.datatype.DatatypeFactory
import kotlin.collections.ArrayList

class Journalpost(
    val systemID: SystemID,
    val journalposttype: String,
    val tittel: String,
    val opprettetDato: Date,
    val opprettetAv: String,
    val arkivertDato: Date,
    val arkivertAv: String,
    val referanseForelderMappe: SystemID,
    val korrespondanseparts: List<Korrespondansepart>,
    val journalstatus: String,
    val referanseEksternNoekkel: EksternNoekkel) : Registrering<no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.Journalpost> {

    override fun buildApiModel() : no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.Journalpost {
        return no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.Journalpost().also {
            it.systemID = systemID.buildApiModel()
            it.journalposttype = journalposttype
            it.tittel = tittel
            it.korrespondansepart.addAll(korrespondanseparts.map { part -> part.buildApiModel() }
                .toCollection(ArrayList()))
            it.journalstatus = journalstatus
            it.referanseEksternNoekkel = referanseEksternNoekkel.buildApiModel()
            it.opprettetDato = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(GregorianCalendar().also { it.time = opprettetDato })
            it.opprettetAv = opprettetAv
            it.arkivertDato = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(GregorianCalendar().also { it.time = arkivertDato })
            it.arkivertAv = arkivertAv
            it.referanseForelderMappe = referanseForelderMappe.buildApiModel()
        }
    }
}

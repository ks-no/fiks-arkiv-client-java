package no.ks.fiks.io.arkiv.model

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Koordinatsystem
import no.ks.fiks.io.arkiv.model.arkivmelding.OrganisasjonsIDBuilder
import no.ks.fiks.io.arkiv.model.arkivmelding.PersonIDBuilder
import no.ks.fiks.io.arkiv.model.arkivmelding.PunktBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.ForenkletDokument
import no.ks.fiks.io.arkiv.model.forenklet.UtgaaendeJournalpost
import org.junit.jupiter.api.Test

class BuilderSetterTest {

    @Test
    fun `PersonIDBuilder landkode settes på PersonID`() {
        val personID = PersonIDBuilder().identifikator("12345678901").landkode("NO").buildApiModel()

        personID.landkode shouldBe "NO"
    }

    @Test
    fun `OrganisasjonsIDBuilder landkode settes på OrganisasjonsID`() {
        val organisasjonsID = OrganisasjonsIDBuilder().identifikator("910825526").landkode("NO").buildApiModel()

        organisasjonsID.landkode shouldBe "NO"
    }

    @Test
    fun `PunktBuilder z settes på Punkt`() {
        val punkt = PunktBuilder()
            .koordinatsystem(Koordinatsystem().apply { kode = "EPSG:25833" })
            .x(1.0)
            .y(2.0)
            .z(3.0)
            .build()

        punkt.z shouldBe 3.0
    }

    @Test
    fun `ForenkletDokument skjermetDokument settes`() {
        ForenkletDokument().skjermetDokument(true).skjermetDokument shouldBe true
    }

    @Test
    fun `UtgaaendeJournalpost vedlegg settes`() {
        val vedlegg = ForenkletDokument().tittel("Vedlegg")

        UtgaaendeJournalpost().vedlegg(listOf(vedlegg)).vedlegg shouldContainExactly listOf(vedlegg)
    }
}

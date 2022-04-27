package no.ks.fiks.io.arkiv.model.forenklet

import no.ks.fiks.io.arkiv.model.arkivmelding.KorrespondansepartBuilder
import no.ks.fiks.io.arkiv.model.arkivmelding.MappeArkivmelding
import no.ks.fiks.io.arkiv.model.arkivmelding.RegistreringArkivmelding
import no.ks.fiks.io.arkiv.model.arkivstruktur.*
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.*

const val SKJERMINGSHJEMMEL: String = "Offl. § 26.1"

class ArkivmeldingForenkletUtgaaende {

    var referanseSaksmappeForenklet: SaksmappeForenklet? = null
        private set
    var nyUtgaaendeJournalpost: UtgaaendeJournalpost? = null
        private set
    var sluttbrukerIdentifikator: String? = null
        private set

    fun referanseSaksmappeForenklet(saksmappe: SaksmappeForenklet) = apply { this.referanseSaksmappeForenklet = saksmappe }
    fun nyUtgaaendeJournalpost(journalpost: UtgaaendeJournalpost) = apply { this.nyUtgaaendeJournalpost = journalpost }
    fun sluttbrukerIdentifikator(sluttbrukerIdentifikator: String) = apply { this.sluttbrukerIdentifikator = sluttbrukerIdentifikator }

    fun byggArkivmelding(): no.ks.fiks.io.arkiv.model.arkivmelding.Arkivmelding {
        checkNotNull(nyUtgaaendeJournalpost) {"ArkivmeldingForenkletUtgaaende krever journalpost"}

        val journalpost = nyUtgaaendeJournalpost?.let {
            val builder = JournalpostBuilder()
                .journalposttype(JournalpostType.UTGAENDE_DOKUMENT)
                .journalstatus(JournalStatus.JOURNALFORT)
            it.tittel?.let { tittel -> builder.tittel(tittel) }
            it.journalaar?.let { aar -> builder.journalaar(aar) }
            it.journalsekvensnummer?.let { nummer -> builder.journalsekvensnummer(nummer) }
            it.journalpostnummer?.let { post -> builder.journalpostnummer(post) }
            it.sendtDato?.let { dato -> builder.sendtDato(dato) }
            it.dokumentetsDato?.let { dato -> builder.dokumentetsDato(dato) }
            it.offentlighetsvurdertDato?.let { dato -> builder.offentlighetsvurdertDato(dato) }
            this.sluttbrukerIdentifikator?.let {bruker ->
                builder.opprettetAv(bruker)
                builder.opprettetAv(bruker)
            }?: throw IllegalStateException("SluttbrukerIdentifkator er påkrevd for Arkivmelding")

            if(it.skjermetTittel){
                builder.skjerming(SkjermingBuilder()
                    .skjermingshjemmel(SKJERMINGSHJEMMEL)
                    .skjermingMetadataTyper(listOf(SkjermingMetadataType.SKJERMING_TITTEL_DOKUMENTBESKRIVELSE, SkjermingMetadataType.SKJERMING_NAVN_PART_I_SAK)))
            }
            it.hoveddokument?.let {doc ->
                val dokumentBuilder = DokumentbeskrivelseBuilder()
                    .dokumentType(doc.dokumenttype ?: DokumentType.KORRESPONDANSE)
                    .dokumentStatus(DokumentStatus.DOKUMENTET_ER_FERDIGSTILT)
                    .tittel(checkNotNull(doc.tittel) { "Tittel er påkrevd for hoveddokument" })
                    .tilknyttetRegistreringSom(TilknyttetRegistreringSomType.HOVEDDOKUMENT)
                if(doc.skjermetDokument == true) {
                    dokumentBuilder.skjerming(SkjermingBuilder()
                        .skjermingshjemmel(SKJERMINGSHJEMMEL)
                        .skjermingDokumentType(SkjermingDokumentType.SKJERMING_AV_HELE_DOKUMENTET)
                    )
                }
                dokumentBuilder.dokumentobjekter(listOf(getDokumentObjektBuilder(doc)))
                builder.dokumentbeskrivelser(builder.dokumentbeskrivelser + dokumentBuilder)
            }
            it.vedlegg.forEach {
                    doc ->
                val dokumentBuilder = DokumentbeskrivelseBuilder()
                    .dokumentType(doc.dokumenttype ?: DokumentType.KORRESPONDANSE)
                    .dokumentStatus(DokumentStatus.DOKUMENTET_ER_FERDIGSTILT)
                    .tittel(checkNotNull(doc.tittel) { "Tittel er påkrevd for hoveddokument" })
                    .tilknyttetRegistreringSom(TilknyttetRegistreringSomType.VEDLEGG)

                dokumentBuilder.dokumentobjekter(listOf(getDokumentObjektBuilder(doc)))
                builder.dokumentbeskrivelser(builder.dokumentbeskrivelser + dokumentBuilder)
            }

            builder.antallVedlegg(builder.dokumentbeskrivelser.size.toLong())

            it.mottakere.forEach { mottaker ->
                val korrespondansepartBuilder = korrespondansepartMapper(KorrespondansepartType.MOTTAKER, mottaker)
                builder.korrespondanseparts(builder.korrespondanseparts + korrespondansepartBuilder)
            }
            it.avsender.forEach { avsender ->
                val korrespondansepartBuilder = korrespondansepartMapper(KorrespondansepartType.AVSENDER, avsender)
                builder.korrespondanseparts(builder.korrespondanseparts + korrespondansepartBuilder)
            }
            it.internAvsender.forEach { avsender ->
                val korrespondansepartBuilder = korrespondansepartMapper(KorrespondansepartType.INTER_AVSENDER, avsender)
                builder.korrespondanseparts(builder.korrespondanseparts + korrespondansepartBuilder)
            }
            it.referanseEksternNoekkelForenklet?.let { rn ->
                val referanseEksternNoekkel = EksternNoekkelBuilder()
                rn.fagstystem?.let { fs -> referanseEksternNoekkel.fagstystem(fs) }
                rn.noekkel?.let { n -> referanseEksternNoekkel.noekkel(n) }
                builder.referanseEksternNoekkel(referanseEksternNoekkel)
            }
        } ?: throw IllegalStateException("UtgaaendeJournalpost er påkrevd for ForenkletUtgåendeArkvimelding")

        this.referanseSaksmappeForenklet?.let { saksmappe ->
            val saksMappeBuilder = opprettSaksmappe(saksmappe, journalpost)

            val arkivmelding = MappeArkivmelding()
                .mapper(listOf(saksMappeBuilder))
                .antallFiler(journalpost.dokumentbeskrivelser.size)
            journalpost.referanseEksternNoekkel?.fagsystem?.let { fs -> arkivmelding.system(fs) }
            journalpost.referanseEksternNoekkel?.noekkel?.let { mid -> arkivmelding.meldingId(mid) }
            return arkivmelding
        } ?: run {
            val arkivmelding = RegistreringArkivmelding()
                .registrering(listOf(journalpost))
                .antallFiler(journalpost.dokumentbeskrivelser.size)
            journalpost.referanseEksternNoekkel?.fagsystem?.let { fs -> arkivmelding.system(fs) }
            journalpost.referanseEksternNoekkel?.noekkel?.let { mid -> arkivmelding.meldingId(mid) }
            return arkivmelding
        }

    }

    private fun opprettSaksmappe(
        saksmappe: SaksmappeForenklet,
        journalpost: JournalpostBuilder
    ): SaksmappeBuilder {
        val saksMappeBuilder = SaksmappeBuilder()
        saksmappe.saksansvarlig?.let { a -> saksMappeBuilder.saksansvarlig(a) }
        saksmappe.administrativEnhet?.let { e -> saksMappeBuilder.administrativEnhet(e) }
        saksmappe.tittel?.let { t -> saksMappeBuilder.tittel(t) }
        saksmappe.saksaar?.let { aar -> saksMappeBuilder.saksaar(aar) }
        saksmappe.sakssekvensnummer?.let { sekvens -> saksMappeBuilder.sakssekvensnummer(sekvens) }
        saksmappe.saksdato?.let { dato -> saksMappeBuilder.saksdato(dato) }
        saksmappe.klasse.let { klasser ->
            saksMappeBuilder.klassifikasjoner(
                klasser.map { k ->
                    val klassifikasjonBuilder = KlassifikasjonBuilder()
                    k.klassifikasjonssystem?.let { k -> klassifikasjonBuilder.klassifikasjonssystem(k) }
                    k.klasseID?.let { k -> klassifikasjonBuilder.klasseID(k) }
                    k.tittel?.let { k -> klassifikasjonBuilder.tittel(k) }
                    klassifikasjonBuilder
                }.toList()
            )
        }
        saksmappe.referanseEksternNoekkelForenklet?.let { noekkel ->
            val noekkelBuilder = EksternNoekkelBuilder()
            noekkel.fagstystem?.let { f -> noekkelBuilder.fagstystem(f) }
            noekkel.noekkel?.let { n -> noekkelBuilder.noekkel(n) }
            saksMappeBuilder.referanseEksternNoekkel(noekkelBuilder)
        }

        saksMappeBuilder.registreringer(listOf(journalpost))
        return saksMappeBuilder
    }

    private fun korrespondansepartMapper(type: KorrespondansepartType, part: KorrespondansepartIntern): KorrespondansepartBuilder {
        val korrespondansepartBuilder = KorrespondansepartBuilder()
        part.saksbehandler?.let {
            korrespondansepartBuilder.korrespondansepartNavn(it)
            korrespondansepartBuilder.saksbehandler(it)
        } ?: part.administrativEnhet?.let {
            korrespondansepartBuilder.korrespondansepartNavn(it)
            korrespondansepartBuilder.administrativEnhet(it)
        }
        korrespondansepartBuilder.korrespondansepartType(type)
        return korrespondansepartBuilder
    }

    private fun korrespondansepartMapper(type: KorrespondansepartType, part: KorrespondansepartForenklet): KorrespondansepartBuilder {
        val korrespondansepartBuilder = KorrespondansepartBuilder()
        korrespondansepartBuilder.korrespondansepartType(type)
        korrespondansepartBuilder.korrespondansepartNavn(checkNotNull(part.navn) { "Navn er påkrevd for mottaker" })

        part.postadresse?.let { adresse ->
            adresse.adresselinje1?.let { a ->
                korrespondansepartBuilder.postadresse(korrespondansepartBuilder.postadresse + a)
            }
            adresse.adresselinje2?.let { a ->
                korrespondansepartBuilder.postadresse(korrespondansepartBuilder.postadresse + a)
            }
            adresse.adresselinje3?.let { a ->
                korrespondansepartBuilder.postadresse(korrespondansepartBuilder.postadresse + a)
            }
            adresse.landkode?.let { land -> korrespondansepartBuilder.land(land) }
            adresse.postnr?.let { postnr -> korrespondansepartBuilder.postnummer(postnr) }
            adresse.poststed?.let { poststed -> korrespondansepartBuilder.poststed(poststed) }
            part.kontaktperson?.let { kontaktperson -> korrespondansepartBuilder.kontaktperson(kontaktperson) }
            part.kontaktinformasjonForenklet?.epostadresse?.let { epost ->
                korrespondansepartBuilder.epostadresse(
                    epost
                )
            }
            part.kontaktinformasjonForenklet?.mobiltelefon?.let { m ->
                korrespondansepartBuilder.telefonnumre(korrespondansepartBuilder.telefonnumre + m)
            }
            part.kontaktinformasjonForenklet?.telefon?.let { t ->
                korrespondansepartBuilder.telefonnumre(korrespondansepartBuilder.telefonnumre + t)
            }
        }

        part.deresReferanse?.let { ref -> korrespondansepartBuilder.deresReferanse(ref) }
        part.forsendelsemåte?.let { fm -> korrespondansepartBuilder.forsendelsesmaate(fm) }
        part.enhetsidentifikator?.organisasjonsnummer?.let { org ->
            korrespondansepartBuilder.organisasjonid(org)
            part.enhetsidentifikator?.landkode?.let { land -> korrespondansepartBuilder.land(land) }
        }
        part.personid?.personidentifikatorNr?.let { person ->
            korrespondansepartBuilder.organisasjonid(person)
            part.enhetsidentifikator?.landkode?.let { land -> korrespondansepartBuilder.land(land) }
        }
        return korrespondansepartBuilder
    }

}

private fun getDokumentObjektBuilder(doc: ForenkletDokument) =
    DokumentObjektBuilder()
        .format(filtypeTilFormatKode(checkNotNull(doc.filnavn) { "Filnavn er påkrevd for hoveddokument" }))
        .variantformat(VariantFormatType.PRODUKSJONSFORMAT)
        .versjonsnummer(1)
        .filnavn(checkNotNull(doc.filnavn) { "Filnavn er påkrevd for hoveddokument" })
        .referanseDokumentfil(checkNotNull(doc.referanseDokumentFil) { "ReferanseDokumentFil er påkrevd for hoveddokument" })


private fun filtypeTilFormatKode(filnavn: String) : FormatType {
    val postfix = filnavn.substring(filnavn.lastIndexOf(".") ?: -1)
    return when {
        postfix.equals(".txt", true) -> return FormatType.REN_TEKST
        postfix.equals(".tiff", true) -> return FormatType.TIFF_VERSJON_6
        postfix.equals(".pdf", true) -> return FormatType.PDF_A_ISO_19005_1_2005
        postfix.equals(".xml", true) -> return FormatType.XML
        postfix.equals(".jpeg", true) -> return FormatType.JPEG
        postfix.equals(".mp3", true) -> return FormatType.MP3
        postfix.equals(".sosi", true) -> return FormatType.SOSI
        postfix.equals(".mpg", true) -> return FormatType.MPEG_2
        postfix.equals(".mp2", true) -> return FormatType.MPEG_2
        else -> throw IllegalStateException("Filformat er ukjent for hoveddokument: ${filnavn}")
    }
}
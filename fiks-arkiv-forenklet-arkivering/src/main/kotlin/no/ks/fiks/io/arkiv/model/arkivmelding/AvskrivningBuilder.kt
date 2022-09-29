package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Avskrivning
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ReferanseTilJournalpost
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.AvskrivningsmaateType
import java.time.LocalDate

class AvskrivningBuilder {
    var avskrivningsdato: LocalDate? = null
        private set
    var avskrevetAv: String? = null
        private set
    var avskrivningsmaate: AvskrivningsmaateType? = null
        private set
    var referanseAvskrivesAvJournalpost: ReferanseTilJournalpost? = null
        private set

    fun avskrivningsdato(avskrivningsdato: LocalDate) = apply { this.avskrivningsdato = avskrivningsdato }
    fun avskrevetAv(avskrevetAv: String) = apply { this.avskrevetAv = avskrevetAv }
    fun avskrivningsmaate(avskrivningsmaate: AvskrivningsmaateType) = apply { this.avskrivningsmaate =  avskrivningsmaate }
    fun referanseAvskrivesAvJournalpost(referanseAvskrivesAvJournalpost: ReferanseTilJournalpost) = apply { this.referanseAvskrivesAvJournalpost = referanseAvskrivesAvJournalpost }

    fun build(): Avskrivning {
        return Avskrivning().also {
            it.avskrivningsdato = checkNotNull(avskrivningsdato) { "Avskrivningsdato er påkrevd for Avskrivning" }
            it.avskrevetAv = checkNotNull(avskrevetAv) { "AvskrevetAv er påkrevd for Avskrivning" }
            it.avskrivningsmaate = avskrivningsmaate?.value ?: throw IllegalStateException("Avskrivningsmaate er påkrevd for Avskrivning")
            it.referanseAvskrivesAvJournalpost = referanseAvskrivesAvJournalpost
        }
    }
}

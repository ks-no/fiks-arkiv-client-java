package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Gradering
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.GradType
import java.time.ZonedDateTime

class GraderingBuilder {
    var grad: GradType? = null
        private set
    var graderingsdato: ZonedDateTime? = null
        private set
    var gradertAv: String? = null
        private set
    var nedgraderingsdato: ZonedDateTime? = null
        private set
    var nedgradertAv: String? = null
        private set

    fun grad(grad: GradType) = apply { this.grad = grad }
    fun graderingsdato(graderingsdato: ZonedDateTime) = apply { this.graderingsdato = graderingsdato }
    fun gradertAv(gradertAv: String) = apply { this.gradertAv = gradertAv }
    fun nedgraderingsdato(nedgraderingsdato: ZonedDateTime) = apply { this.nedgraderingsdato = nedgraderingsdato }
    fun nedgradertAv(nedgradertAv: String) = apply { this.nedgradertAv = nedgradertAv }

    fun build() : Gradering {
        return Gradering().also {
            it.grad = grad?.value ?: throw IllegalStateException("Grad er påkrevd for Gradering")
            it.graderingsdato = checkNotNull(graderingsdato) {"Graderingsdato er påkrevd for Gradering"}
            it.gradertAv = checkNotNull(gradertAv) {"GradertAv er påkrevd for Gradering"}
            it.nedgraderingsdato = nedgraderingsdato
            it.nedgradertAv = nedgradertAv
        }
    }
}

package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Arkivmelding

class MappeArkivmeldingBuilder: ArkivmeldingBuilder() {

    var mapper: List<MappeBuilder>? = emptyList()
        private set

    fun mapper(mapper: List<MappeBuilder>) = apply { this.mapper = mapper }

    override fun build(): Arkivmelding {
        return super.build().also {
            it.mappes.addAll( mapper?.map { m -> m.build() }?.toList() ?: emptyList() )
        }
    }

}
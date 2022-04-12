package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.io.arkiv.model.arkivstruktur.MappeBuilder

class MappeArkivmelding: Arkivmelding() {

    var mapper: List<MappeBuilder>? = emptyList()
        private set

    fun mapper(mapper: List<MappeBuilder>) = apply { this.mapper = mapper }

    override fun build(): no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Arkivmelding {
        return super.build().also {
            it.mappes.addAll( mapper?.map { m -> m.build() }?.toList() ?: emptyList() )
        }
    }

}
package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Klasse

class MappeKlasseBuilder: KlasseBuilder() {
    var mapper: List<MappeBuilder> = ArrayList()
        private set

    fun mapper(mapper: List<MappeBuilder>) = apply { this.mapper = mapper }

    override fun build(): Klasse {
        return super.build().also {
            it.mappes.addAll(mapper.map { m -> m.build() }.toList())
        }
    }
}
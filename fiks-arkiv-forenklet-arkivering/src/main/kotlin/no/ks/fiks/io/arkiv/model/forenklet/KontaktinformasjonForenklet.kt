package no.ks.fiks.io.arkiv.model.forenklet

class KontaktinformasjonForenklet {
    var epostadresse: String? = null
    var mobiltelefon: String? = null
    var telefon: String? = null

    fun epostadresse(epostadresse: String) = apply { this.epostadresse =  epostadresse }
    fun mobiltelefon(mobiltelefon: String) = apply { this.mobiltelefon =  mobiltelefon }
    fun telefon(telefon: String) = apply { this.telefon =  telefon }
}

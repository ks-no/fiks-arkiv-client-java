package no.ks.fiks.io.arkiv.model.forenklet

class EksternNoekkelForenklet {
    var fagstystem: String? = null
        private set
    var noekkel: String? = null
        private set

    fun fagstystem(fagstystem: String) = apply { this.fagstystem = fagstystem }
    fun noekkel(noekkel: String) = apply { this.noekkel = noekkel }
}
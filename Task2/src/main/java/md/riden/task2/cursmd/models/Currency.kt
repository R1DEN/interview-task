package md.riden.task2.cursmd.models

class Currency(val name: String, val rate: Rate) {

    override fun toString(): String {
        return "$name    $rate$"
    }
}
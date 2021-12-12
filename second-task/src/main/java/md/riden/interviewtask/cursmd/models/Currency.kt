package md.riden.interviewtask.cursmd.models

class Currency(val name: String, val rate: Rate) {

    override fun toString(): String {
        return "$name    $rate"
    }
}
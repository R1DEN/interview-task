package md.riden.interviewtask.cursmd.models

enum class CSVType {
    BNM, BANK, CSV;

    companion object {
        fun parse(className: String): CSVType {
            return when (className) {
                "badge-central-bank" -> BNM
                "badge-bank" -> BANK
                "badge-csv" -> CSV
                else -> throw RuntimeException("Not able to parse bank")
            }
        }
    }
}
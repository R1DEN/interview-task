package md.riden.interviewtask.cursmd.models

class BankList(banks: List<Bank>) {
    private val banks: List<Bank> = banks
        get() = field.filter { it.locality == "Chisinau" }

    fun getBankByName(name: String): Bank {
        return banks.find { bank -> bank.name.contains(name, true) }
            ?: throw RuntimeException("No bank with $name in it's name has been found")
    }
}
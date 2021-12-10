package md.riden.interviewtask.cursmd.models

class BankList(banks: List<Bank>) {
    private val banks: List<Bank> = banks
        get() = field.filter { it.locality == "Chisinau" }

    fun getRatesForBank(name: String): List<Currency>? {
        return banks.find { bank -> bank.name.contains(name, true) }?.currencies
    }
}
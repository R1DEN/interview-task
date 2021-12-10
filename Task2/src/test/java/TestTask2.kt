import com.google.gson.Gson
import md.riden.task2.cursmd.core.CursMd
import org.junit.Test
import java.time.LocalDate

class TestTask2 {
    @Test
    fun task2(){
        val ekk = CursMd().getBankListForDate(LocalDate.now())
        println(Gson().toJson(ekk))
    }
}
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActivityInfo(
    val id: Int?,
    val user: String?,
    val date: String,
    val distance: Double?,
    val timeSpentHours: Int?,
    val timeSpentMinutes: Int?,
    val activityType: String?,
    val timePassed: String?,
    val timeStart: String?,
    val timeFinish: String?,
    val comment: String?
) : Parcelable
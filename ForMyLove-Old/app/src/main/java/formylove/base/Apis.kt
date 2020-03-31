package formylove.base

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

const val GITHUB_BASE_URL = "https://api.github.com/"
const val GITHUB_CONTENT_URL = "repos/TotoroXkf/ForMyLoveData/contents/"

const val HEAD_IMAGE_JSON_URL = GITHUB_BASE_URL + GITHUB_CONTENT_URL + "HeadImage/data.json"
const val STATEMENT_JSON_RUL = GITHUB_BASE_URL + GITHUB_CONTENT_URL + "Statement/StatementData.json"


interface GithubApi {
    @GET
    fun getFileContent(@Url url: String): Call<GithubContentData>
    
    @PUT
    fun updateFileContent(@Url url: String, @Body githubUpdateBody: GithubUpdateBody): Call<ResponseBody>
}
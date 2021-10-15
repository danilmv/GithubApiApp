package com.andriod.githubapiapp.model

import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.utils.postDelayed
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.lang.reflect.Type

class DummyDataProvider : DataProvider() {
    private val searchResultsType: Type = object : TypeToken<List<User>>() {}.type
    private val behaviorSubject = BehaviorSubject.create<List<User>>()

    override fun readData(): Observable<List<User>> {
        dataHandler.postDelayed(SLEEP_TIME) {
            users.addAll(Gson().fromJson(EXAMPLE_JSON, searchResultsType))
            behaviorSubject.onNext(users)
        }
        return behaviorSubject
    }

    companion object {
        const val EXAMPLE_JSON = "[\n" +
                "  { \"login\": \"mojombo\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/1?v=4\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/mojombo/repos\" },\n" +
                "  { \"login\": \"defunkt\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/2?v=4\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/defunkt/repos\" },\n" +
                "  {\"login\": \"pjhyett\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/3?v=4\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/pjhyett/repos\" },\n" +
                "  {\"login\": \"wycats\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/4?v=4\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/wycats/repos\" },\n" +
                "  {\"login\": \"ezmobius\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/5?v=4\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/ezmobius/repos\" },\n" +
                "  {\"login\": \"ivey\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/6?v=4\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/ivey/repos\" },\n" +
                "  {\"login\": \"evanphx\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/7?v=4\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/evanphx/repos\" },\n" +
                "  {\"login\": \"vanpelt\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/17?v=4\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/vanpelt/repos\" },\n" +
                "  {\"login\": \"wayneeseguin\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/18?v=4\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/wayneeseguin/repos\" },\n" +
                "  {\"login\": \"brynary\", \"avatar_url\": \"https://avatars.githubusercontent.com/u/19?v=4\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/brynary/repos\" }]"
    }
}
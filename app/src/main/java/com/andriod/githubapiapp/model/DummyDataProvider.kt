package com.andriod.githubapiapp.model

import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.eventbus.EventBus
import com.andriod.githubapiapp.utils.EventDislike
import com.andriod.githubapiapp.utils.EventLike
import com.andriod.githubapiapp.utils.postDelayed
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import java.lang.reflect.Type

class DummyDataProvider : DataProvider() {
    private val searchResultsType: Type = object : TypeToken<List<User>>() {}.type
    private val behaviorSubject = BehaviorSubject.create<List<User>>()

    init {
        EventBus.eventObservable
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { event ->
                    when (event) {
                        is EventLike -> {
                            event.user.rating++
                        }
                        is EventDislike -> {
                            event.user.rating--
                        }
                    }
                    behaviorSubject.onNext(users)
                },
                {})
    }

    override fun readUsers(): Observable<List<User>> {
        dataHandler.postDelayed(SLEEP_TIME) {
            users.addAll(Gson().fromJson(EXAMPLE_JSON, searchResultsType))
            behaviorSubject.onNext(users)
        }
        return behaviorSubject
    }

    override fun readUserRepos(user: User): Observable<List<Repo>> {
        TODO("Not yet implemented")
    }

    override fun saveUsers(users: List<User>):Completable {
        TODO("Not yet implemented")
    }

    override fun saveRepos(repos: List<Repo>):Completable {
        TODO("Not yet implemented")
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
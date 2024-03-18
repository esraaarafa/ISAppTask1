
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val view = LoginViewConsole()
    val presenter = LoginPresenterImpl(view)

    println("Enter username:")
    val username = scanner.nextLine()

    println("Enter password:")
    val password = scanner.nextLine()

    presenter.validateCredentials(username, password)
}

interface LoginModel {
    fun login(username: String, password: String, listener: OnLoginFinishedListener)
}

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun showError(message: String)
    fun showSuccess()
}

interface LoginPresenter {
    fun validateCredentials(username: String, password: String)
}

interface OnLoginFinishedListener {
    fun onLoginSuccess()
    fun onLoginError(message: String)
}

class LoginModelImpl : LoginModel {
    override fun login(username: String, password: String, listener: OnLoginFinishedListener) {
        // Simulate login process
        if (username == "esraa1154" && password == "12345") {
            listener.onLoginSuccess()
        } else {
            listener.onLoginError("Invalid credentials")
        }
    }
}

class LoginPresenterImpl(private val view: LoginView) : LoginPresenter, OnLoginFinishedListener {
    private val model: LoginModel = LoginModelImpl()

    override fun validateCredentials(username: String, password: String) {
        view.showProgress()
        model.login(username, password, this)
    }

    override fun onLoginSuccess() {
        view.hideProgress()
        view.showSuccess()
    }

    override fun onLoginError(message: String) {
        view.hideProgress()
        view.showError(message)
    }
}

class LoginViewConsole : LoginView {
    override fun showProgress() {
        println("Logging in...")
    }

    override fun hideProgress() {
        // Not needed in console
    }

    override fun showError(message: String) {
        println("Error: $message")
    }

    override fun showSuccess() {
        println("Login successful")
    }
}

package com.winterry.nbcsignin.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.winterry.nbcsignin.R
import com.winterry.nbcsignin.databinding.ActivitySignUpBinding
import com.winterry.nbcsignin.domain.UserDataValidator
import com.winterry.nbcsignin.presentation.model.UserEvent
import com.winterry.nbcsignin.presentation.util.asText
import com.winterry.nbcsignin.presentation.viewmodel.SignUpViewModel
import com.winterry.nbcsignin.presentation.viewmodel.SignUpViewModelFactory

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val editTextList: List<EditText> by lazy {
        listOf(
            binding.nameInputEditText.apply { tag = EditTextTag.NAME_EDIT_TEXT },
            binding.emailInputEditText.apply { tag = EditTextTag.EMAIL_EDIT_TEXT },
            binding.idInputEditText.apply { tag = EditTextTag.ID_EDIT_TEXT },
            binding.passwordInputEditText.apply { tag = EditTextTag.PASSWORD_EDIT_TEXT },
            binding.passwordConfirmInputEditText.apply {
                tag = EditTextTag.PASSWORD_CONFIRM_EDIT_TEXT
            }
        )
    }
    private val signUpViewModel: SignUpViewModel by viewModels {
        SignUpViewModelFactory(UserDataValidator())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
        setTextChangedListeners()
        setFocusChangeListeners()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.signUpButton.setOnClickListener {
            signUpViewModel.registerAuth()
        }
    }

    private fun setObservers() {
        signUpViewModel.apply {
            userEvent.observe(this@SignUpActivity) { event ->
                when (event) {
                    is UserEvent.Error -> {
                        makeToast(event.msg)
                    }

                    is UserEvent.RegisterSuccess -> {
                        val userInfo = event.user
                        makeToast(userInfo.asText())
                        val intent = Intent().putExtra(
                            SignInActivity.USER_INFO,
                            arrayListOf(userInfo.id, userInfo.password)
                        )
                        setResult(RESULT_OK, intent)
                        finish()
                    }

                    is UserEvent.None -> {}
                    UserEvent.Success -> {}
                }
            }

            nameValid.observe(this@SignUpActivity) { event ->
                when (event) {
                    is UserEvent.Error -> binding.nameWarnTextView.text = event.msg
                    UserEvent.Success -> binding.nameWarnTextView.text = ""
                    else -> {}
                }
            }

            emailValid.observe(this@SignUpActivity) { event ->
                when (event) {
                    is UserEvent.Error -> binding.emailWarnTextView.text = event.msg
                    UserEvent.Success -> binding.emailWarnTextView.text = ""
                    else -> {}
                }
            }

            idValid.observe(this@SignUpActivity) { event ->
                when (event) {
                    is UserEvent.Error -> binding.idWarnTextView.text = event.msg
                    UserEvent.Success -> binding.idWarnTextView.text = ""
                    else -> {}
                }
            }

            passwordValid.observe(this@SignUpActivity) { event ->
                when (event) {
                    is UserEvent.Error -> {
                        binding.passwordWarnTextView.setTextColor(getColor(R.color.red))
                        binding.passwordWarnTextView.text = event.msg
                    }

                    is UserEvent.None -> {
                        binding.passwordWarnTextView.setTextColor(getColor(R.color.grey))
                        binding.passwordWarnTextView.text = event.msg
                    }

                    UserEvent.Success -> binding.passwordWarnTextView.text = ""
                    else -> {}
                }
            }

            passwordConfirmValid.observe(this@SignUpActivity) { event ->
                when (event) {
                    is UserEvent.Error -> binding.passwordConfirmWarnTextView.text = event.msg
                    UserEvent.Success -> binding.passwordConfirmWarnTextView.text = ""
                    else -> {}
                }
            }

            allInfoValid.observe(this@SignUpActivity) {
                binding.signUpButton.isEnabled = it
            }
        }
    }

    private fun setTextChangedListeners() {
        editTextList.forEach { editTextView ->
            editTextView.doAfterTextChanged {
                when (editTextView.tag as EditTextTag) {
                    EditTextTag.NAME_EDIT_TEXT -> signUpViewModel.validateName(it.toString())
                    EditTextTag.EMAIL_EDIT_TEXT -> signUpViewModel.validateEmail(it.toString())
                    EditTextTag.ID_EDIT_TEXT -> signUpViewModel.validateId(it.toString())
                    EditTextTag.PASSWORD_EDIT_TEXT -> {
                        signUpViewModel.validatePassword(it.toString())
                        signUpViewModel.validatePasswordConfirm(
                            it.toString(),
                            binding.passwordConfirmInputEditText.text.toString()
                        )
                    }

                    EditTextTag.PASSWORD_CONFIRM_EDIT_TEXT -> signUpViewModel.validatePasswordConfirm(
                        binding.passwordInputEditText.text.toString(),
                        it.toString()
                    )
                }
            }
        }
    }

    private fun setFocusChangeListeners() {
        editTextList.forEach { editTextView ->
            editTextView.setOnFocusChangeListener { view, _ ->
                when (view.tag as EditTextTag) {
                    EditTextTag.NAME_EDIT_TEXT -> signUpViewModel.validateName(editTextView.text.toString())
                    EditTextTag.EMAIL_EDIT_TEXT -> signUpViewModel.validateEmail(editTextView.text.toString())
                    EditTextTag.ID_EDIT_TEXT -> signUpViewModel.validateId(editTextView.text.toString())
                    EditTextTag.PASSWORD_EDIT_TEXT -> {
                        signUpViewModel.validatePassword(editTextView.text.toString())
                        signUpViewModel.validatePasswordConfirm(
                            editTextView.text.toString(),
                            binding.passwordConfirmInputEditText.text.toString()
                        )
                    }

                    EditTextTag.PASSWORD_CONFIRM_EDIT_TEXT -> signUpViewModel.validatePasswordConfirm(
                        binding.passwordInputEditText.text.toString(),
                        editTextView.text.toString()
                    )
                }
            }
        }
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    enum class EditTextTag {
        NAME_EDIT_TEXT,
        EMAIL_EDIT_TEXT,
        ID_EDIT_TEXT,
        PASSWORD_EDIT_TEXT,
        PASSWORD_CONFIRM_EDIT_TEXT
    }
}
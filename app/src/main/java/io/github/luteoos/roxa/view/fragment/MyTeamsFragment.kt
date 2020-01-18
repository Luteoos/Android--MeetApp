package io.github.luteoos.roxa.view.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import io.github.luteoos.roxa.baseAbstract.BaseFragment
import io.github.luteoos.roxa.viewmodel.MainScreenViewModel
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.recyclerview.RVMyTeams
import io.github.luteoos.roxa.model.Group
import io.github.luteoos.roxa.utils.DialogWithTextInput
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.view.activity.CreateEventDialogActivity
import io.github.luteoos.roxa.view.activity.FreeTimeDialogActivity
import kotlinx.android.synthetic.main.fragment_my_teams.*

class MyTeamsFragment : BaseFragment<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_my_teams

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(activity!!)
        observeData()
        refresh()
        setBindings()
    }

    override fun refresh() {
        viewModel.getTeams()
    }

    private fun observeData(){
        viewModel.getTeamsLiveData().observe(this, Observer { list ->
            setRVTeams(list)
        })
    }

    private fun setBindings(){
        btnCreateTeam.setOnClickListener {
            showCreateTeamDialog()
        }
        btnJoinTeam.setOnClickListener {
            showJoinTeamDialog()
        }
    }

    private fun setRVTeams(list: MutableList<Group>){
        rvMyTeams.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RVMyTeams(context, list){uuid, parameter ->
                handleRVButtons(uuid, parameter)
            }
        }
    }

    private fun showCreateTeamDialog(){
        DialogWithTextInput(context!!).apply {
            setTitle(R.string.dialog_create_team_title)
            setMessage(R.string.dialog_create_team_message)
            setPositiveButton(android.R.string.ok){_,_ ->
                viewModel.createTeam(this.getEditTextContent())
                return@setPositiveButton
            }
            setNegativeButton(android.R.string.cancel){dialog, _ ->
                dialog.cancel()
                return@setNegativeButton
            }
            show()
        }
    }

    private fun showJoinTeamDialog(){
        DialogWithTextInput(context!!).apply {
            setTitle(R.string.dialog_join_team_title)
            setMessage(R.string.dialog_join_team_message)
            setPositiveButton(android.R.string.ok){_,_ ->
                viewModel.joinTeam(this.getEditTextContent())
                return@setPositiveButton
            }
            setNegativeButton(android.R.string.cancel){dialog, _ ->
                dialog.cancel()
                return@setNegativeButton
            }
            show()
        }
    }

    private fun showLeaveTeamDialog(teamId: String){
        DialogWithTextInput(context!!).apply {
            setTitle(R.string.dialog_leave_team_title)
            setMessage(R.string.dialog_leave_team_message)
            hideEditText(true)
            setPositiveButton(android.R.string.ok){_,_ ->
                viewModel.leaveTeam(teamId)
                return@setPositiveButton
            }
            setNegativeButton(android.R.string.cancel){dialog, _ ->
                dialog.cancel()
                return@setNegativeButton
            }
            show()
        }
    }

    private fun handleRVButtons(uuid: String, parameter: String){
        when(parameter){
            Parameters.LEAVE_TEAM -> showLeaveTeamDialog(uuid)
            Parameters.SHOW_TEAM_INVITATION -> copyTeamInvitationToClipboard(uuid)
            Parameters.CREATE_EVENT -> createEvent(uuid)
        }
    }

    private fun copyTeamInvitationToClipboard(teamInvitation: String){
        (context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).let {clipboardManager ->
            clipboardManager.primaryClip = ClipData.newPlainText("Team Invitation", teamInvitation)
        }
        Toasty.info(context!!, R.string.copied_team_invitation_to_clip).show()
    }

    private fun createEvent(teamUUID: String){
        activity?.let {
            val intent = Intent(it, CreateEventDialogActivity::class.java)
            intent.putExtra(Parameters.TEAM_ID, teamUUID)
            it.startActivityForResult(intent, Parameters.OPEN_DIALOG_ACTIVITY)
        }
    }
}
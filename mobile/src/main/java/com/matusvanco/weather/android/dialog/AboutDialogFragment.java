package com.matusvanco.weather.android.dialog;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;

import com.matusvanco.weather.android.R;
import com.matusvanco.weather.android.databinding.DialogAboutBinding;


public class AboutDialogFragment extends DialogFragment
{
	private DialogAboutBinding mBinding;


	public static AboutDialogFragment newInstance()
	{
		return new AboutDialogFragment();
	}


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setCancelable(true);
		setStyle(STYLE_NORMAL, 0);
	}


	@Override
	@TargetApi(Build.VERSION_CODES.M)
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		mBinding = DialogAboutBinding.inflate(getActivity().getLayoutInflater());
		mBinding.setData(getMessage());

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.dialog_about_title)
				.setView(mBinding.getRoot())
				.setPositiveButton(R.string.dialog_about_button_text, (dialog, which) ->
				{
				});

		Dialog dialog = builder.create();

		@ColorInt int bgDialogColor;
		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk >= Build.VERSION_CODES.M)
		{
			bgDialogColor = getResources().getColor(R.color.global_bg_dialog, null);
		}
		else
		{
			bgDialogColor = getResources().getColor(R.color.global_bg_dialog);
		}
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.global_bg_dialog)));

		return dialog;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		// cancelable on touch outside
		if (getDialog() != null)
		{
			getDialog().setCanceledOnTouchOutside(true);
		}
	}


	@Override
	public void onResume()
	{
		super.onResume();
		setFixedDialogWidth(R.dimen.dialog_about_width);
	}


	private String getMessage()
	{
		return getString(R.string.dialog_about_message);
	}


	private void setFixedDialogWidth(@DimenRes int dimenRes)
	{
		int width = getResources().getDimensionPixelSize(dimenRes);
		getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
	}
}
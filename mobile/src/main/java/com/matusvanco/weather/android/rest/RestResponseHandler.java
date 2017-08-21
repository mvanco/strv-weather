package com.matusvanco.weather.android.rest;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.matusvanco.weather.android.R;
import com.matusvanco.weather.android.STRVWeatherApplication;
import com.matusvanco.weather.android.entity.ErrorEntity;

import org.alfonz.rest.HttpException;
import org.alfonz.rest.ResponseHandler;

import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Response;


public class RestResponseHandler implements ResponseHandler
{
	@Override
	public boolean isSuccess(Response<?> response)
	{
		return response.isSuccessful();
	}


	@Override
	public String getErrorMessage(HttpException exception)
	{
		return ((ErrorEntity) exception.error()).getMessage();
	}


	@Override
	public String getFailMessage(Throwable throwable)
	{
		int resId;

		if (throwable instanceof UnknownHostException)
			resId = R.string.global_network_unknown_host;
		else if (throwable instanceof FileNotFoundException)
			resId = R.string.global_network_not_found;
		else if (throwable instanceof SocketTimeoutException)
			resId = R.string.global_network_timeout;
		else if (throwable instanceof JsonParseException)
			resId = R.string.global_network_parse_fail;
		else if (throwable instanceof MalformedJsonException)
			resId = R.string.global_network_parse_fail;
		else if (throwable instanceof ParseException)
			resId = R.string.global_network_parse_fail;
		else if (throwable instanceof NumberFormatException)
			resId = R.string.global_network_parse_fail;
		else if (throwable instanceof ClassCastException)
			resId = R.string.global_network_parse_fail;
		else
			resId = R.string.global_network_fail;

		return STRVWeatherApplication.getContext().getString(resId);
	}


	@Override
	public HttpException createHttpException(Response<?> response)
	{
		return new RestHttpException(response);
	}
}

package com.matusvanco.weather.android.rest;

import com.matusvanco.weather.android.entity.ErrorEntity;

import org.alfonz.rest.HttpException;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class RestHttpException extends HttpException
{
	public RestHttpException(Response<?> response)
	{
		super(response);
	}


	@Override
	public Object parseError(Response<?> response)
	{
		try
		{
			Converter<ResponseBody, ErrorEntity> converter = WeatherRetrofitClient.getRetrofit().responseBodyConverter(ErrorEntity.class, new Annotation[0]);
			return converter.convert(response.errorBody());
		} catch (IOException | NullPointerException e)
		{
			e.printStackTrace();
			ErrorEntity error = new ErrorEntity();
			error.setMessage("Unknown error");
			return error;
		}
	}
}
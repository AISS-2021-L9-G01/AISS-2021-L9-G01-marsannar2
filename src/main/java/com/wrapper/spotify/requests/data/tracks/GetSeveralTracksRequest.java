package com.wrapper.spotify.requests.data.tracks;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Track;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;
import java.util.List;

public class GetSeveralTracksRequest extends AbstractRequest {

  private GetSeveralTracksRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Track[] get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new Track.JsonUtil().createModelObjectArray(getJson(), "tracks");
  }

  public SettableFuture<Track[]> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return executeAsync(new Track.JsonUtil().createModelObjectArray(getJson(), "tracks"));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(final List<String> ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids);
      setPath("/v1/tracks");
      return setFormParameter("ids", idsParameter);
    }

    @Override
    public GetSeveralTracksRequest build() {
      return new GetSeveralTracksRequest(this);
    }

  }

}

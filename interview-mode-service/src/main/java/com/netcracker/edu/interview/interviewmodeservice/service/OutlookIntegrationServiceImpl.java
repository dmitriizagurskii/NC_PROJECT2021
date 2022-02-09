package com.netcracker.edu.interview.interviewmodeservice.service;

import com.microsoft.graph.authentication.BaseAuthenticationProvider;
import com.microsoft.graph.models.DateTimeTimeZone;
import com.microsoft.graph.models.Event;
import com.microsoft.graph.requests.GraphServiceClient;
import com.netcracker.edu.interview.interviewmodeservice.dto.InterviewScheduling.ScheduleInterviewRequest;
import com.netcracker.edu.interview.interviewmodeservice.interfaces.OutlookIntegrationService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class OutlookIntegrationServiceImpl implements OutlookIntegrationService {

    private static final String EVENT_SUBJECT = "Interview";
    private static final String TIME_ZONE = "Europe/Moscow";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public String scheduleMeeting(ScheduleInterviewRequest scheduleInterviewRequest, OAuth2AuthorizedClient graphAuthorizedClient) {
        final GraphServiceClient graphServiceClient = getGraphServiceClient(graphAuthorizedClient);

        Event event = buildEvent(scheduleInterviewRequest.getDate());

        Event respEvent = graphServiceClient.me().events()
                .buildRequest()
                .post(event);
        log.info(respEvent.toString());

        return respEvent.id;
    }

    @Override
    public void rescheduleMeeting(String outlookId, LocalDateTime dateTime, OAuth2AuthorizedClient graphAuthorizedClient) {
        final GraphServiceClient graphServiceClient = getGraphServiceClient(graphAuthorizedClient);

        Event event = buildEvent(dateTime);

        Event respEvent = graphServiceClient.me().events(outlookId)
                                                    .buildRequest()
                                                    .patch(event);
    }

    @Override
    public void cancelMeeting(String outlookId, OAuth2AuthorizedClient graphAuthorizedClient) {
        final GraphServiceClient graphServiceClient = getGraphServiceClient(graphAuthorizedClient);

        Event respEvent = graphServiceClient.me().events(outlookId)
                                                    .buildRequest()
                                                    .delete();
    }

    private static Event buildEvent(LocalDateTime startDateTime) {
        Event event = new Event();
        event.subject = EVENT_SUBJECT;
        DateTimeTimeZone start = new DateTimeTimeZone();
        start.dateTime = startDateTime.format(DATE_TIME_FORMATTER);
        start.timeZone = TIME_ZONE;
        event.start = start;
        DateTimeTimeZone end = new DateTimeTimeZone();
        end.dateTime = startDateTime.plusMinutes(90).format(DATE_TIME_FORMATTER);
        end.timeZone = TIME_ZONE;
        event.end = end;
        return event;
    }

    private static GraphServiceClient getGraphServiceClient(@NonNull OAuth2AuthorizedClient graphAuthorizedClient) {
        return GraphServiceClient.builder().authenticationProvider(new GraphAuthenticationProvider(graphAuthorizedClient))
                .buildClient();
    }


    private static class GraphAuthenticationProvider
            extends BaseAuthenticationProvider {

        private OAuth2AuthorizedClient graphAuthorizedClient;

        /**
         * Set up the GraphAuthenticationProvider. Allows accessToken to be
         * used by GraphServiceClient through the interface IAuthenticationProvider
         *
         * @param graphAuthorizedClient OAuth2AuthorizedClient created by AAD Boot starter. Used to surface the access token.
         */
        public GraphAuthenticationProvider(@NonNull OAuth2AuthorizedClient graphAuthorizedClient) {
            this.graphAuthorizedClient = graphAuthorizedClient;
        }

        /**
         * This implementation of the IAuthenticationProvider helps injects the Graph access
         * token into the headers of the request that GraphServiceClient makes.
         *
         * @param requestUrl the outgoing request URL
         * @return a future with the token
         */
        @Override
        public CompletableFuture<String> getAuthorizationTokenAsync(@NonNull final URL requestUrl){
            return CompletableFuture.completedFuture(graphAuthorizedClient.getAccessToken().getTokenValue());
        }
    }
}

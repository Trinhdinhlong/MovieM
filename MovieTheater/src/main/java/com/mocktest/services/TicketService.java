package com.mocktest.services;
import com.mocktest.bean.request.BookingTicketRequest;
import com.mocktest.bean.response.*;
import com.mocktest.entities.*;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.ErrorCode;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.mocktest.exceptions.ErrorCode.ERROR_DATA_NOT_MATCH;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ShowTimeService showTimeService;
    @Autowired
    private SeatService seatService;
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    public List<BookingTicketResponse> saveTicket(List<BookingTicketRequest> bookingTicketRequests) {
        List<BookingTicketResponse> bookingTicketResponseList = new ArrayList<>();
        for (BookingTicketRequest bookingTicketRequest : bookingTicketRequests) {
            User user = userService.getById(bookingTicketRequest.getUserId());
            ShowTime showTime = showTimeService.getById(bookingTicketRequest.getShowTimeId());
            Seat seat = seatService.getById(bookingTicketRequest.getSeatId());
            Ticket ticket = new Ticket();
            BookingTicketResponse bookingTicketResponse = new BookingTicketResponse();
            ticket.setStartTime(showTime.getStartTime());
            ticket.setTicketType(TicketStatus.Waiting_for_ticket);
            ticket.setSeat(seat);
            ticket.setShowTime(showTime);
            ticket.setUser(user);
            ticket.setEndTime(showTime.getEndTime());
            Ticket ticketSaved = ticketRepository.save(ticket);
            bookingTicketResponse.setUsername(ticketSaved.getUser().getUsername());
            bookingTicketResponse.setCreatedTime(ticketSaved.getCreatedDate());
            bookingTicketResponse.setStartTime(ticketSaved.getStartTime());
            bookingTicketResponse.setSeatColumn(ticketSaved.getSeat().getSeatColumn());
            bookingTicketResponse.setSeatRow(ticketSaved.getSeat().getSeatRow());
            bookingTicketResponse.setSeatType(ticketSaved.getSeat().getSeatType());
            bookingTicketResponse.setPrice(ticketSaved.getSeat().getPrice());
            bookingTicketResponseList.add(bookingTicketResponse);
        }
        return bookingTicketResponseList;
    }
    public Ticket getTicketHasActiveTrue(Long id) {
        return ticketRepository.getTicketActive(id, LocalTime.now());
    }

    public void UpdateStatusTicket(Long id) {
        ticketRepository.UpdateStatusTicket(id, TicketStatus.Got_the_ticket);
    }

    public ConfirmTicketResponse getConfirmAdminByTicketId(Long id) {
        return ticketRepository.getTicketById(id);
    }

    public List<BookedAndCancelTicketResponse> getAllBookedList(String username) {
        List<BookedAndCancelTicketResponse> responses = new ArrayList<>();
        List<BookedAndCancelTicketResponse> bookedTicketResponseList = ticketRepository.getTotalPriceByTicket(username);
        if (bookedTicketResponseList != null && !responses.isEmpty()) {
            bookedTicketResponseList.sort(Comparator.comparing(response -> {
                if (response.getStartDate() != null) {
                    return response.getStartDate();
                } else {
                    throw new NotFoundException(ERROR_DATA_NOT_MATCH);
                }
            }, Comparator.nullsLast(Comparator.reverseOrder())));
        }
        for (BookedAndCancelTicketResponse bookedTicketResponse : bookedTicketResponseList) {
            TicketStatus ticketType = bookedTicketResponse.getTicketType();
            if (ticketType == TicketStatus.Got_the_ticket || ticketType == TicketStatus.Waiting_for_ticket) {
                responses.add(bookedTicketResponse);
            }
        }
        return responses;
    }
    public List<BookedAndCancelTicketResponse> getAllCancelList(String username) {
        List<BookedAndCancelTicketResponse> responses = new ArrayList<>();
        List<BookedAndCancelTicketResponse> bookedTicketResponseList = ticketRepository.getTotalPriceByTicket(username);
        if (bookedTicketResponseList != null && !responses.isEmpty()) {
            bookedTicketResponseList.sort(Comparator.comparing(response -> {
                if (response.getStartDate() != null) {
                    return response.getStartDate();
                } else {
                    throw new NotFoundException(ERROR_DATA_NOT_MATCH);
                }
            }, Comparator.nullsLast(Comparator.reverseOrder())));
        }
        for (BookedAndCancelTicketResponse bookedTicketResponse : bookedTicketResponseList) {
            TicketStatus ticketType = bookedTicketResponse.getTicketType();
            if (ticketType == TicketStatus.Abort) {
                responses.add(bookedTicketResponse);
            }
        }
        if (responses.isEmpty()) {
            throw new NotFoundException(ErrorCode.ERROR_DB_NOT_FOUND);
        }
        return responses;
    }

    public List<HistoryTicketResponse> getAllHistoryList(String username) {
        List<HistoryTicketResponse> responses = new ArrayList<>();
        List<BookedAndCancelTicketResponse> bookedTicketResponseList = ticketRepository.getTotalPriceByTicket(username);
        if (bookedTicketResponseList != null && !responses.isEmpty()) {
            bookedTicketResponseList.sort(Comparator.comparing(response -> {
                if (response.getStartDate() != null) {
                    return response.getStartDate();
                } else {
                    throw new NotFoundException(ERROR_DATA_NOT_MATCH);
                }
            }, Comparator.nullsLast(Comparator.reverseOrder())));
        }
        for (BookedAndCancelTicketResponse bookedTicketResponse : bookedTicketResponseList) {
            HistoryTicketResponse historyTicketResponse = new HistoryTicketResponse();
            historyTicketResponse.setCreateDate(bookedTicketResponse.getStartDate());
            historyTicketResponse.setMovieNameVN(bookedTicketResponse.getMovieNameVN());
            responses.add(historyTicketResponse);
        }
        if (responses.isEmpty()) {
            throw new NotFoundException(ErrorCode.ERROR_DB_NOT_FOUND);
        }
        return responses;
    }

    public List<BookingListResponse> getAllBookingUser() {
        List<BookingListResponse> responses = ticketRepository.getAllBookingUser();
        if (responses != null && !responses.isEmpty()) {
            responses.sort(Comparator.comparing(response -> {
                if (response.getCreatedDate() != null) {
                    return response.getCreatedDate();
                } else {
                    throw new NotFoundException(ERROR_DATA_NOT_MATCH);
                }
            }, Comparator.nullsLast(Comparator.reverseOrder())));
        }
        return responses;
    }

    public List<BookingListResponse> searchAllBookingUserByUserName(String data) {
        List<BookingListResponse> responses = ticketRepository.SearchAllBookingUser(data);
        System.out.println(responses);
        if (responses != null && !responses.isEmpty()) {
            responses.sort(Comparator.comparing(response -> {
                if (response.getCreatedDate() != null) {
                    return response.getCreatedDate();
                } else {
                    throw new NotFoundException(ERROR_DATA_NOT_MATCH);
                }
            }, Comparator.nullsLast(Comparator.reverseOrder())));
            if (responses.isEmpty()) {
                throw new NotFoundException(ErrorCode.ERROR_DB_NOT_FOUND);
            }
            return responses;
        }
        throw new BadRequestException(ERROR_DATA_NOT_MATCH);
    }
}

package com.noah.backend.domain.trade.repository.custom.impl;

import com.noah.backend.domain.trade.dto.requestDto.TradeGetReqDto;
import com.noah.backend.domain.trade.dto.responseDto.TradeDateAndTime;
import com.noah.backend.domain.trade.dto.responseDto.TradeGetResDto;
import com.noah.backend.domain.trade.entity.Trade;
import com.noah.backend.domain.trade.repository.TradeRepository;
import com.noah.backend.domain.trade.repository.custom.TradeRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static com.noah.backend.domain.trade.entity.QTrade.trade;

@RequiredArgsConstructor
public class TradeRepositoryImpl implements TradeRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<List<TradeGetResDto>> getTradeList(Long accountId, TradeGetReqDto tradeGetReqDto) {
        String startDate = tradeGetReqDto.getStartDate();
        String endDate = tradeGetReqDto.getEndDate();
        return Optional.ofNullable(query.select(
                        Projections.constructor(TradeGetResDto.class,
                                trade.id, trade.type, trade.name, trade.date, trade.time, trade.cost, trade.amount))
                .from(trade).where(trade.account.id.eq(accountId), trade.date.between(startDate, endDate))
                .fetch());
    }

    @Override
    public Optional<TradeDateAndTime> getTradeDateAndTime(String date, String time) {
        return Optional.ofNullable(query.select(Projections.constructor(TradeDateAndTime.class, trade.date, trade.time))
                .from(trade)
                .where(trade.date.eq(date).and(trade.time.eq(time)))
                .fetchOne());
    }
}

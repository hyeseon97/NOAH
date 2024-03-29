package com.noah.backend.domain.comment.repository.custom;

import com.noah.backend.domain.comment.dto.responseDto.CommentGetDto;
import com.noah.backend.domain.comment.dto.responseDto.CommentListGetDto;
import com.noah.backend.domain.comment.entity.Comment;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

//import static com.noah.backend.domain.comment.entity.QComment.comment;
import static com.noah.backend.domain.comment.entity.QComment.comment;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory query;


    @Override
    public Optional<List<CommentListGetDto>> getCommentList(Long reviewId) {
        List<CommentListGetDto> commentDtos = query
                .select(constructor(CommentListGetDto.class,
                        comment.id,
                        comment.member.id,
                        comment.content
                ))
                .from(comment)
                .where(comment.review.id.eq(reviewId))
                .fetch();

        return Optional.ofNullable(commentDtos);
    }

    @Override
    public Optional<CommentGetDto> getCommentSelect(Long commentId) {
        CommentGetDto commentDto = query
                .select(Projections.constructor(CommentGetDto.class,
//                        comment.id,
                        comment.content))
                .from(comment)
                .where(comment.id.eq(commentId))
                .fetchOne();

        return Optional.ofNullable(commentDto);
    }

    @Override
    public Optional<Comment> findCommentByMemberIdAndTravelId(Long memberId, Long reviewId) {
        return Optional.ofNullable(query.select(comment)
                                       .from(comment)
                                       .where(comment.member.id.eq(memberId).and(comment.review.id.eq(reviewId)))
                                       .fetchOne());
    }
}

FROM ruby:2.3

ENV RAILS_ENV development

WORKDIR /app
ADD Gemfile Gemfile.lock /app/

RUN bundle install -j5 --retry 10
RUN bundle exec rails db:migrate

ADD . /app

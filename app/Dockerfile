FROM ruby:2.3

ENV RAILS_ENV production
ENV RAILS_LOG_TO_STDOUT true

WORKDIR /app
ADD Gemfile Gemfile.lock /app/

RUN bundle install -j5 --retry 10

ADD . /app

RUN RAILS_ENV=production rake assets:precompile

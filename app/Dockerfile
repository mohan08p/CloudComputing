FROM ruby:2.3

ENV RAILS_ENV development

WORKDIR /app
ADD Gemfile Gemfile.lock /app/

RUN bundle install -j5 --retry 10

ADD . /app

CMD bundle exec puma -C config/puma.rb

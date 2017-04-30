# Base image:
FROM nginx

# Install dependencies
RUN apt-get update -qq && apt-get -y install apache2-utils

# establish where Nginx should look for files
ENV PROJECT_ROOT /home/ubuntu/Docker-ELK-Rails

# Set our working directory inside the image
WORKDIR $PROJECT_ROOT

# Remove the default Nginx configuration file
# RUN rm -v /etc/nginx/nginx.conf

# create log directory
RUN mkdir log
RUN touch log/nginx.access.log
RUN touch log/nginx.error.log

# copy over static assets
# COPY public public/

# Copy Nginx config template
COPY config/nginx.conf /tmp/docker_example.nginx
COPY config/.htpasswd /etc/nginx/.htpasswd

# substitute variable references in the Nginx config template for real values from the environment
# put the final config in its place
RUN envsubst '$PROJECT_ROOT' < /tmp/docker_example.nginx > /etc/nginx/conf.d/default.conf
RUN rm -rf /etc/nginx/sites-available/default
ADD config/nginx.conf /etc/nginx/sites-enabled/nginx.conf

EXPOSE 80

# Use the "exec" form of CMD so Nginx shuts down gracefully on SIGTERM (i.e. `docker stop`)
CMD [ "nginx", "-g", "daemon off;" ]

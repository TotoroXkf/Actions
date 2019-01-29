from django.contrib import admin
from django.urls import path, include
from rest_framework import routers
from snippets import views

urlpatterns = [
    path('', include('snippets.urls'))
]

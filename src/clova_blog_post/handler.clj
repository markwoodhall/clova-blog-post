(ns clova-blog-post.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clova.core :as clova]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def to-do-validation (clova/validation-set [:title clova/required? clova/stringy? [clova/shorter? 255]
                                             :date clova/required? clova/date?
                                             [:meta :category] clova/stringy? [clova/shorter? 255]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

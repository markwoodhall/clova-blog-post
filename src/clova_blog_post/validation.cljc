(ns clova-blog-post.validation
  (:require [clova.core :as clova]))

(def to-do-validation
  (clova/validation-set [:title clova/required? clova/stringy? [clova/longer? 10] [clova/shorter? 255]
                         :date clova/required? clova/date?
                         [:meta :category] clova/stringy? [clova/longer? 10] [clova/shorter? 255]]))

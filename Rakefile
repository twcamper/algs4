require 'rake/clean'
require File.expand_path 'scripts/rake/algs4'

task :foo do
  Algs4::Src::Main::Java.find_changed
  Algs4::Src::Main::Java::Changed.each do |k, v|
    p k
    v.each {|f| puts "\t#{f}"}
  end
end
# clobber and clean lists for Rake default tasks
CLOBBER.include(FileList["#{Algs4::BIN_ROOT}/*"])
CLEAN.include(FileList["#{Algs4::BIN_ROOT}/**/*.{jar,class}"])

desc "local source needed in jars on the classpath"
task :lib do
  Algs4::Src::Main::Java::Lib.find_changed
  Algs4::Src::Main::Java::Lib.build
end

desc "source for book exercises"
task :src => :lib do
  Algs4::Src::Main::Java.find_changed
  Algs4::Src::Main::Java.build
end

desc "build jars and exercise source"
task :all => [:check_classpath, :lib, :src]
task :default => :all

desc "compile junit tests"
task :build_tests => :all do
  Algs4::Src::Test::Java.find_changed
  Algs4::Src::Test::Java.build
end

desc "run junit tests"
task :test => :build_tests do
  Algs4::Src::Test::Java.run_tests(ENV['TEST'])
end

desc "warn and exit if no classpath"
task :check_classpath do
  include Test::Unit::Assertions

  assert_not_nil(ENV['CLASSPATH'], "no classpath set")
end

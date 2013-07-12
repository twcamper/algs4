require 'rake/clean'
require 'fileutils'
require 'popen4'
require 'test/unit/assertions'

module Algs4
  BIN_ROOT   = File.expand_path "target/classes"

  def find_changed
    self::FILES.each do |src_file|
      out_file = src_file.sub(self::SRC, self::BIN).sub(/\.java$/,".class")
      if !File.exist?(out_file) || File.mtime(src_file) > File.mtime(out_file)
        out_dir = File.dirname(out_file)
        if self::Changed[out_dir]
          self::Changed[out_dir] << src_file
        else
          self::Changed[out_dir] = [src_file]
        end
      end
    end
  end

  def run(cmd)
    puts cmd = "time " + cmd
    status = POpen4::popen4(cmd) do |stdout, stderr|
      puts stdout.read.strip
      # color the console output of stderr
      puts "\033[0;36m" + stderr.read.strip + "\033[0m"
    end
    raise "Command Failed" unless status.exitstatus == 0
  end

  module Lib
    extend Test::Unit::Assertions
    extend ::Algs4  # include find_changed(), run() as singletons
    SRC   = File.expand_path "lib"
    BIN   = "#{Algs4::BIN_ROOT}/lib"
    FILES = Dir["#{SRC}/**/*.java"]
    Changed = {}

    def self.build
      unless Changed.empty?
        src_list = Changed.values.flatten
        src_list.each do |f|
          package_name = File.dirname(f).sub("#{SRC}/", "")
          assert_match(File.read(f), /^\s*package\s+#{package_name};/)
        end
        FileUtils.mkdir_p BIN, :verbose => false
        puts "Compile:"
        run "javac -d #{BIN} #{src_list.join(' ')}"
      end
    end

    def self.package
      unless Changed.empty?
        Dir.chdir(BIN)
        class_file_list = Dir["**/*.class"]
        puts "Package:"
        run "jar cf #{BIN}.jar #{class_file_list.join(' ')}"
      end
    end
  end

  module Src
    module Main
      module Java
        extend ::Algs4
        SRC   = File.expand_path "src/main/java"
        BIN   = Algs4::BIN_ROOT
        FILES = Dir["#{SRC}/**/*.java"]
        Changed = {}

        def self.build
          Changed.each do |out_dir, src_list|
            FileUtils.mkdir_p out_dir, :verbose => false
            run "javac -d #{out_dir} #{src_list.join(' ')}"
          end
        end
      end
    end
  end
end

# clobber and clean lists for Rake default tasks
CLOBBER.include(FileList["#{Algs4::BIN_ROOT}/*"])
CLEAN.include(FileList["#{Algs4::BIN_ROOT}/**/*.{jar,class}"])

desc "local source needed in jars on the classpath"
task :lib do
  Algs4::Lib.find_changed
  Algs4::Lib.build
end

desc "archive lib files for use on classpath"
task :package => :lib do
  Algs4::Lib.package
end

desc "source for book exercises"
task :src do
  Algs4::Src::Main::Java.find_changed
  Algs4::Src::Main::Java.build
end

desc "build jars and exercise source"
task :all => [:check_classpath, :package, :src]
task :default => :all

desc "warn and exit if no classpath"
task :check_classpath do
  include Test::Unit::Assertions

  assert_not_nil(ENV['CLASSPATH'], "no classpath set")
end

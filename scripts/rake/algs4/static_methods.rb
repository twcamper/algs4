require 'popen4'
require 'fileutils'
module Algs4
  module StaticMethods

    def find_changed
      self::FILES.each do |src_file|
        info = BuildInfo.new(src_file, self::SRC, self::BIN)

        if !File.exist?(info.target_file) || File.mtime(src_file) > File.mtime(info.target_file)
          out_dir = info.target_dir_for_javac
          if self::Changed[out_dir]
            self::Changed[out_dir] << src_file
          else
            self::Changed[out_dir] = [src_file]
          end
        end
      end
    end

    def build
      self::Changed.each do |out_dir, src_list|
        FileUtils.mkdir_p(out_dir, :verbose => false)
        run_shell "javac -d #{out_dir} #{src_list.join(' ')}"
      end
    end

    def run_shell(cmd)
      puts cmd = "time " + cmd
      status = POpen4::popen4(cmd) do |stdout, stderr|
        puts stdout.read.strip
        # color the console output of stderr
        puts "\033[0;36m" + stderr.read.strip + "\033[0m"
      end
      raise "Command Failed" unless status.exitstatus == 0
    end

  end
end
